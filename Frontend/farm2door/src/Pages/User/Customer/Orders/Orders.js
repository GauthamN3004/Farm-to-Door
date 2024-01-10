import Layout from "../../../../Component/Layout/Layout";
import { useAuth } from "../../../../Context/AuthContext";
import { toast } from 'react-hot-toast';
import { useEffect, useState } from "react";
import "./Orders.css"
import axios from "axios";
import CustomerPanel from "../../../../Component/Layout/CustomerPanel/CustomerPanel";
import CustomerOrderCard from "../../../../Component/CustomerOrderCard/CustomerOrderCard";

function Orders() {
    const {isLoggedIn, userData, login, logout} = useAuth();
    const [orders, setOrders] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    const authHeader = {
        username: userData.username,
        password: userData.raw_password,
    };

    const fetchOrders = async () => {
        try{
            const response = await axios.get(`http://localhost:8080/api/customer/${userData.userId}/order`, {auth: authHeader});
            if(response.status == 200){
                setOrders(response.data);
            }
            setIsLoading(false);
        } catch(error){
            toast.error("Could not fetch orders");
        }
    }

    useEffect(() => {
        fetchOrders();
    }, [])


    return (
        <Layout>
            <div className="container">
                <div className="row">
                    <div className="col-sm-3">
                        <CustomerPanel activeTab={"my-orders"} />
                    </div>
                    <div className="col-sm-9">
                        <h3>MY ORDERS</h3>
                        {
                            (isLoading) ? <>
                                <div className="d-flex justify-content-center">
                                    <div className="spinner-border" role="status">
                                        <span className="sr-only"></span>
                                    </div>
                                </div>
                            </> : (orders.map((order) => ( <CustomerOrderCard key={order.id} orderData={order} /> )))
                        }
                    </div>
                </div>
            </div>
        </Layout>
    )
}

export default Orders;