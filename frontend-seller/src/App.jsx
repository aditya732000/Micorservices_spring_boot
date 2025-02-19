import './App.css'
import { Route, Routes } from 'react-router'
import Login from './pages/Login'
import Orders from './pages/Orders'
import Dashboard from './pages/Dashboard'
import CreateProduct from './pages/CreateProduct'
import SidebarLayout from './components/SidebarLayout'
import SignUp from './pages/SignUp'
import RequireAuth from './components/RequireAuth'
import ProductsList from './pages/Products'

function App() {

  return (
    <Routes>
      <Route path='/login' element={<Login/>}/>
      <Route path='/signup' element={<SignUp />}/>
      <Route element={<RequireAuth/>}>
        <Route element={<SidebarLayout />}>
            <Route path="/" element={<Dashboard />} />
            <Route path="/orders" element={<Orders />} />
            <Route path="/products" element={<ProductsList />} />
            <Route path="/create-product" element={<CreateProduct />} />
        </Route>
      </Route>
    </Routes>
  )
}

export default App
