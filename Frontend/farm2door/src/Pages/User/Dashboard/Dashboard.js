import Layout from "../../../Component/Layout/Layout";
import { Navigate } from "react-router-dom";
import { useAuth } from "../../../Context/AuthContext";
import { toast } from 'react-hot-toast';

function Dashboard() {
    const {isLoggedIn, userData, login, logout} = useAuth();
    
    if (!isLoggedIn) {
        toast.error("Login Required");
        return <Navigate to="/login" />;
    }
    
    return (
        <Layout>
            <h1>Dashboard</h1>
        </Layout>
    )
}

export default Dashboard;