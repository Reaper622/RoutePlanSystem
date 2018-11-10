package com.may.routeplansystem.algorithm.imp;

import com.may.routeplansystem.algorithm.Algorithm;
import com.may.routeplansystem.dao.DistanceDao;
import com.may.routeplansystem.dao.NodeDao;
import com.may.routeplansystem.dao.SolutionDao;
import com.may.routeplansystem.entity.po.Distance;
import com.may.routeplansystem.entity.po.Solution;
import com.may.routeplansystem.entity.vo.RouteTemp;
import com.may.routeplansystem.pojo.NodePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.may.routeplansystem.util.MMath.*;


@Component
public class GeneticAlgorithm implements Algorithm {

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private DistanceDao distanceDao;

    @Autowired
    private SolutionDao solutionDao;


    @Override
    public void executeAlgorithm(int questionId) {
        List<NodePojo> centerNodes = nodeDao.selectCenterNode(questionId);
        List<NodePojo> serviceNodes = nodeDao.selectServiceNode(questionId);
        int minDistance;
        int solutionCount = 50;
        int hybridMytationCount = 2000;
        int i = 0;
        List<RouteTemp> solutions = initializeRoute(solutionCount, serviceNodes, centerNodes);
        if (!solutions.isEmpty()) {
            TreeMap<Integer, RouteTemp> treeMap = saveDistanceAndRoute(solutions);
            minDistance = treeMap.firstKey();

            while (i < hybridMytationCount) {
                treeMap = hybridMutation(treeMap);
                int min = treeMap.firstKey();
                if (minDistance == min) {
                    i++;
                } else {
                    minDistance = min;
                    i = 0;
                }
            }

            List<Solution> list;
            Set<Map.Entry<Integer, RouteTemp>> set = treeMap.entrySet();
            Iterator<Map.Entry<Integer, RouteTemp>> iterator = set.iterator();
            List<RouteTemp> list1 = new ArrayList<>();
            while (iterator.hasNext()) {
                RouteTemp routeTemp = iterator.next().getValue();
                list1.add(routeTemp);
            }
            for (int j = 0; j < list1.size(); j++) {
                List<List<NodePojo>> nodes = list1.get(j).getRoute();
                list = madeRoute(nodes);
                for (int k = 0; k < list.size(); k++) {
                    solutionDao.insertSolution(list.get(k));
                }
            }
        }
    }

    private List<Solution> madeRoute(List<List<NodePojo>> nodes) {
        List<Solution> list = new ArrayList<>();
        StringBuilder s;

        //路线数
        for (int i = 0; i < nodes.size(); i++) {
            double dis = 0;
            double time = 0;
            s = new StringBuilder();

            List<NodePojo> node = nodes.get(i);
            //每条路线节点数
            for (int k = 0; k < node.size(); k++) {
                NodePojo b = node.get(k);
                s.append(b.getNodeAddress() + ",");
            }

            for (int k = 0; k < node.size() - 1; k++) {
                NodePojo a = node.get(k);
                NodePojo b = node.get(k + 1);
                Distance distance1 = distanceDao.findDistanceByStartIdAndEndId(a.getNodeId(), b.getNodeId());
                dis = dis + distance1.getDis();
                time = time + distance1.getTime();
            }
            Solution route = new Solution();
            route.setTotalTime(time);
            route.setTotalDis(dis);
            route.setRoute(s.toString());
            list.add(route);
        }
        return list;
    }

    private TreeMap<Integer, RouteTemp> hybridMutation(TreeMap<Integer, RouteTemp> treeMap) {
        //先轮盘赌，找到父代
        Map.Entry<Integer, RouteTemp> entry = lunPanDu(treeMap);
        List<List<NodePojo>> route = entry.getValue().getRoute();
        int routeCount = route.size();
        //直接变异
        if (routeCount == 1) {
            List<List<NodePojo>> routeTemp = mutation(route);
            entry.getValue().setRoute(routeTemp);
        } else {
            //默认必须杂交
            route = hybrid(route, routeCount);
            //变异随机
            route = mutation(route);
            int distance = caculateDistance(route);
            int maxDistance = treeMap.lastKey();
            if (!treeMap.containsKey(distance)) {
                if (distance < maxDistance) {
                    treeMap.pollLastEntry();
                    RouteTemp routeTemp = new RouteTemp(route);
                    treeMap.put(distance, routeTemp);
                }
            }
        }
        return treeMap;
    }

    private int caculateDistance(List<List<NodePojo>> route) {
        int size = route.size();
        int totalDis = 0;
        for (int i = 0; i < size; i++) {
            List<NodePojo> nodes = route.get(i);
            int nodeSize = nodes.size();
            for (int j = 0; j < nodeSize - 1; j++) {
                NodePojo a = nodes.get(j);
                NodePojo b = nodes.get(j + 1);
                Distance distance1 = distanceDao.findDistanceByStartIdAndEndId(a.getNodeId(), b.getNodeId());
                totalDis = totalDis + distance1.getDis();
            }
        }
        return totalDis;
    }

    private List<List<NodePojo>> hybrid(List<List<NodePojo>> route, int routeCount) {
        int route1 = (int) (Math.random() * routeCount);
        int route2 = (int) (Math.random() * routeCount);
        while (route1 == route2) {
            route2 = (int) (Math.random() * routeCount);
        }
        List routeWay1 = route.get(route1);
        List routeWay2 = route.get(route2);
        int routeWaySize1 = routeWay1.size();
        int routeWaySize2 = routeWay2.size();
        int routeIndex1 = (int) (Math.random() * routeWaySize1);
        int routeIndex2 = (int) (Math.random() * routeWaySize2);
        //防止取到首和尾
        routeIndex1 = notInHeadAndTail(routeWaySize1, routeIndex1);
        routeIndex2 = notInHeadAndTail(routeWaySize2, routeIndex2);
        //将数组拆成两段,每段都添加
        List head1 = new ArrayList(routeWay1.subList(0, routeIndex1));
        List tail1 = new ArrayList(routeWay1.subList(routeIndex1, routeWaySize1));
        List head2 = new ArrayList(routeWay2.subList(0, routeIndex2));
        List tail2 = new ArrayList(routeWay2.subList(routeIndex2, routeWaySize2));
        head1.addAll(tail2);
        head2.addAll(tail1);
        route.set(route1, head1);
        route.set(route2, head2);
        return route;
    }

    private List<List<NodePojo>> mutation(List<List<NodePojo>> route) {
        int routeSize = route.size();
        int route1 = (int) (Math.random() * routeSize);
        List<NodePojo> routeWay1 = route.get(route1);
        int size = routeWay1.size();
        int node1 = 0;
        int node2 = 0;
        node1 = notInHeadAndTail(size, node1);
        node2 = notInHeadAndTail(size, node2);
        if (node1 == node2) {
            return route;
        } else {
            NodePojo serviceNode = routeWay1.get(node1);
            routeWay1.set(node1, routeWay1.get(node2));
            routeWay1.set(node2, serviceNode);
            return route;
        }
    }

    private int notInHeadAndTail(int routeWaySize1, int routeIndex1) {

        while (routeIndex1 == 0 || routeIndex1 == routeWaySize1 - 1) {
            routeIndex1 = (int) (Math.random() * routeWaySize1);
        }
        return routeIndex1;
    }


    private Map.Entry<Integer, RouteTemp> lunPanDu(TreeMap<Integer, RouteTemp> treeMap) {
        int size = treeMap.size();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            for (int j = i; j <= size; j++) {
                list.add(i);
            }
        }
        int index = list.get((int) Math.random() * list.size());

        Set<Map.Entry<Integer, RouteTemp>> set = treeMap.entrySet();
        Iterator<Map.Entry<Integer, RouteTemp>> iterator = set.iterator();
        Map.Entry<Integer, RouteTemp> parentEntry = null;
        for (int i = 0; i < index; i++) {
            parentEntry = iterator.next();
        }

        return parentEntry;
    }

    private TreeMap<Integer, RouteTemp> saveDistanceAndRoute(List<RouteTemp> routeList) {
        TreeMap<Integer, RouteTemp> map = new TreeMap<>();
        int totalDis = 0;
        List<NodePojo> nodes;
        int routeSize = routeList.size();
        for (int i = 0; i < routeSize; i++) {
            RouteTemp routeTemp = routeList.get(i);
            List<List<NodePojo>> route = routeTemp.getRoute();
            int routeCount = route.size();
            for (int j = 0; j < routeCount; j++) {
                int size = route.get(j).size();
                nodes = route.get(j);
                for (int k = 0; k < size - 1; k++) {
                    NodePojo a = nodes.get(k);
                    NodePojo b = nodes.get(k + 1);
                    Distance distance = distanceDao.findDistanceByStartIdAndEndId(a.getNodeId(), b.getNodeId());
                    totalDis = totalDis + distance.getDis();
                }
            }
            map.put(totalDis, routeTemp);
        }
        return map;
    }

    private List<RouteTemp> initializeRoute(int count, List<NodePojo> serviceNodes,
                                            List<NodePojo> centerNodes) {
        NodePojo centerNode = centerNodes.get(0);
        List<RouteTemp> solutions = new LinkedList<>();
        int routeRandom = 20;
        RouteTemp oneRoute;
        for (int i = 0; i < count; i++) {
            List<NodePojo> serviceNodesCopy = new LinkedList<>(serviceNodes);
            int routeCount = randomIntExceptZero(0, routeRandom);
            oneRoute = new RouteTemp(routeCount);
            for (int j = 0; j < routeCount; j++) {
                if (serviceNodesCopy.isEmpty()) {
                    break;
                }
                List<NodePojo> everyPath = oneRoute.getRoute().get(j);
                everyPath.add(centerNode);
                if (j == routeCount - 1) {
                    everyPath.addAll(serviceNodesCopy);
                    everyPath.add(centerNode);
                    break;
                }
                int serviceNodesCopySize = serviceNodesCopy.size();
                int everyRouteNodeCount = randomIntExceptZero(0, serviceNodesCopySize);
                for (int k = 0; k < everyRouteNodeCount; k++) {
                    int index = randomInt(0, serviceNodesCopySize);
                    NodePojo serviceNode = serviceNodesCopy.get(index);
                    oneRoute.getRoute().get(j).add(serviceNode);
                    serviceNodesCopy.remove(index);
                }
                oneRoute.getRoute().get(j).add(centerNode);
            }
            removeEmptyRoute(oneRoute);
            solutions.add(oneRoute);
        }
        return solutions;
    }

    private void removeEmptyRoute(RouteTemp routeTemp) {
        int k = 0;
        while (k < routeTemp.getRoute().size()) {
            List list = routeTemp.getRoute().get(k);
            if (!list.isEmpty()) {
                k++;
            } else {
                routeTemp.getRoute().remove(k);
            }
        }
    }


}
