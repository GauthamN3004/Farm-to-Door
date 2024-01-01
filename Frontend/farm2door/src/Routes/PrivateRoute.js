import { useAuth } from "../Context/AuthContext";
import toast from 'react-hot-toast';
import { useNavigate, Outlet } from "react-router-dom";

function PrivateRoute() {
    const {isLoggedIn, userData, login, logout} = useAuth();
    const navigate = useNavigate();
    
    if(isLoggedIn){
        return <Outlet />
    }
    toast.error("Login Required !");
    setTimeout(() => {
        navigate("/login");
    }, 10);
}

export default PrivateRoute;