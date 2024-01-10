import Layout from "../../../Component/Layout/Layout";
import { Navigate } from "react-router-dom";
import { useAuth } from "../../../Context/AuthContext";
import { toast } from 'react-hot-toast';
import FarmerPanel from "../../../Component/Layout/FarmerPanel/FarmerPanel";
import "./Dashboard.css"

function Dashboard() {
    const {isLoggedIn, userData, login, logout} = useAuth();
    
    // if (!isLoggedIn) {
    //     toast.error("Login Required");
    //     return <Navigate to="/login" />;
    // }
    
    return (
        <Layout>
            <div className="container">
                <h3>DASHBOARD</h3>
                <div className="row">
                    <div className="col-sm-3">
                        <FarmerPanel />
                    </div>
                    <div className="col-sm-5">
                    One of three columns
                    </div>
                    <div className="col-sm-4">
                    One of three columns
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default Dashboard;