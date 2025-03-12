import { Routes, Route } from "react-router";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import RequireAuth from "./components/RequireAuth";
import ProductList from "./pages/ProductList";
import ProductDetails from "./pages/ProductDetails";
import Cart from "./pages/Cart";
import ShippingInfo from "./pages/ShippingInfo";
import SuccessPage from "./pages/SuccessPage";
import Layout from "./components/Layout";
import SearchProducts from "./pages/SearchProducts";

function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/success" element={<SuccessPage/>}/>
      <Route element={<RequireAuth />}>
        <Route element={<Layout/>}>
          <Route path="/" element={<ProductList />} />
          <Route path="/product/:productId" element={<ProductDetails/>}/>
          <Route path='/cart' element={<Cart/>} />
          <Route path="/address-info" element={<ShippingInfo/>}/>
          <Route path="/search" element={<SearchProducts/>}/>
        </Route>
      </Route>
    </Routes>
  );
}

export default App;
