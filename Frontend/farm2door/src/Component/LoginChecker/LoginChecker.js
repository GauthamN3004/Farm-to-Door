import { useAuth } from "../../Context/AuthContext";
import { useNavigate } from "react-router-dom";
import { toast } from "react-hot-toast";
import { useEffect } from "react";

function LoginChecker({ children }){
    const {isLoggedIn, userData, login, logout} = useAuth();
    const navigate = useNavigate();
    useEffect(() => {
        if (!isLoggedIn) {
            toast.error("Login Required!");
            navigate("/login");
        }
    }, [isLoggedIn, navigate]);

    return isLoggedIn ? children : null;
}

export default LoginChecker;