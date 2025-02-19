/* eslint-disable react/prop-types */
import { useSelector } from "react-redux";
import { useLocation, Navigate, Outlet } from "react-router";
import { selectCurrentToken } from "../redux/slices/authSlice";

const RequireAuth = () => {
  const location = useLocation();
  const token = useSelector(selectCurrentToken);
  console.log(token)

  if (token) {
    return <Outlet/>
  }
  return <Navigate to="/login" state={{ from: location }} />;
};
export default RequireAuth;