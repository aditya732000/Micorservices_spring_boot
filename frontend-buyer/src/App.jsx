import { Routes, Route } from "react-router";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import "./App.css";
import Home from "./pages/Home";
import RequireAuth from "./components/RequireAuth";
import ProductList from "./pages/ProductList";
import ProductDetails from "./pages/ProductDetails";
import Cart from "./pages/Cart";
import ShippingInfo from "./pages/ShippingInfo";

function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route element={<RequireAuth />}>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<ProductList />} />
        <Route path="/product/:productId" element={<ProductDetails/>}/>
        <Route path='/cart' element={<Cart/>} />
        <Route path="/address-info" element={<ShippingInfo/>}/>
      </Route>
    </Routes>
  );
}

export default App;
