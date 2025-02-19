import { useSelector } from "react-redux/es/hooks/useSelector";
import { selectCurrentToken } from "../reducers/authSlice";
import { jwtDecode } from "jwt-decode";

const useAuth = () => {
  const token = useSelector(selectCurrentToken);
  if (token) {
    const decoded = jwtDecode(token);
    console.log(decoded)
    const { userId, role } = decoded;
    return { userId, role };
  }
  return { userId: "", roles: [] };
};

export default useAuth;