//一级路由
import Login from '@/components/Login'
import Register from '@/components/Register'
import SystemHome from '@/components/SystemHome'

//二级路由
import VehicleManagement from '@/components/Home/VehicleManagement'
import HistoryOrder from '@/components/Home/HistoryOrder'
import SystemSetting from '@/components/Home/SystemSetting'
import Path from '@/components/Home/Path'

export const routes = [
  { 
    path: '/login', 
    name: 'loginLink', 
    component: Login 
  },
  { 
    path: '/register', 
    name: 'registerLink', 
    component: Register 
  },
  {
    path: '/home', 
    name: 'homeLink', 
    redirect: '/home/path', 
    component: SystemHome, 
    children: [
      {
        path: '/home/path', 
        name: 'path', 
        component: Path 
      },
      { 
        path: '/home/vehicleManagement', 
        name: 'vehicleManagementLink', 
        component: VehicleManagement 
      },
      { 
        path: '/home/historyOrder', 
        name: 'historyOrderLink', 
        component: HistoryOrder 
      },
      { 
        path: '/home/systemSetting', 
        name: 'systemSetting', 
        component: SystemSetting 
      }
    ]
  }
]
