import axios from "axios";
import FarmerPanel from "../../../../Component/Layout/FarmerPanel/FarmerPanel";
import Layout from "../../../../Component/Layout/Layout";
import "./FarmerOrders.css";
import { toast } from 'react-hot-toast';
import { useAuth } from "../../../../Context/AuthContext";
import { useEffect, useState } from "react";
import FarmerOrderCard from "../../../../Component/FarmerOrderCard/FarmerOrderCard";

function FarmerOrders() {
    const [orders, setOrders] = useState([]);
    const {isLoggedIn, userData, login, logout} = useAuth();
    const authHeader = {
        username: userData.username,
        password: userData.raw_password,
    };

    const fetchFarmerOrders = async () => {
        try{
            const response = await(axios.get(`http://localhost:8080/api/farmer/${userData.userId}/order`));
            if(response.status == 200){
                setOrders(response.data);
            }
        } catch(error){
            toast.error("Could not fetch your orders!");
        }
    }

    const updateOrderStatus = async (orderItemId, statusId) => {
        const response = await axios.post(`http://localhost:8080/api/farmer/${userData.userId}/update-items-status?orderItemId=${orderItemId}&statusId=${statusId}`,null, {auth: authHeader});
        if(response.status == 200){
            toast.success("Order Status Updated Successfully");
            fetchFarmerOrders();
        }
        else {
            toast.error("Could not update status of the order !");
        }
    }

    useEffect(() => {
        fetchFarmerOrders()
    }, []);

    return (
        <Layout>
            <div className="container">
                <div className="row">
                    <div className="col-sm-3">
                        <FarmerPanel activeTab={"orders"} />
                    </div>

                    <div className="col-sm-9">
                        {orders.map((order) => 
                            <div className="card">
                                 <FarmerOrderCard orderData={order} updateOrderStatus={updateOrderStatus}/>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default FarmerOrders;