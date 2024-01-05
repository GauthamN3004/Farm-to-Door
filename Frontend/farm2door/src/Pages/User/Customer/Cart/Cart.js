import Layout from "../../../../Component/Layout/Layout";
import { useAuth } from "../../../../Context/AuthContext";
import { toast } from 'react-hot-toast';
import { useEffect, useState } from "react";
import axios from "axios";
import CustomerPanel from "../../../../Component/Layout/CustomerPanel/CustomerPanel";
import CartCard from "../../../../Component/CartCard/CartCard";

function Cart() {
    const {isLoggedIn, userData} = useAuth();
    const [loading, setLoading] = useState(false);
    const [cartItems, setCartItems] = useState([]);

    const authHeader = {
        username: userData.username,
        password: userData.raw_password,
    };

    const fetchCart = async () => {
        try {
            setLoading(true);
            const response = await axios.get(`http://localhost:8080/api/customer/${userData.userId}/cart`);
            setCartItems(response.data);
            setLoading(false);
        } catch (error) {
            console.error('Error fetching cart items:', error);
            setLoading(false);
        }
    };

    const deleteFromCart = async (cartId) => {
        try{
            const response = await axios.delete(`http://localhost:8080/api/customer/${userData.userId}/cart/${cartId}`, {auth: authHeader});
            if (response.status === 200) {
                fetchCart();
            }
            toast.success("Item Removed from Cart");
        } catch (error){
            toast.error("Could not remove item from cart!");
        }
    }
    
    useEffect(() => {
        fetchCart();
    }, [isLoggedIn, userData]);

    return (
        <Layout>
            <div className="container">
                <div className="row">
                    <div className="col-sm-3">
                        <CustomerPanel activeTab={"cart"} />
                    </div>

                    <div className="col-sm-9">
                        <div className="row myHarvest">
                            {cartItems.map((cartData) => (
                                <div className="col-md-4">
                                    <CartCard cartData={cartData} userRole={userData.role} eventHandle={deleteFromCart}/>
                                </div>
                            ))}
                        </div>
                        {
                            cartItems.length === 0 && !loading ? (
                                <div>
                                    <h3>No Items in Cart</h3>
                                </div>
                            ) : (
                                <div className="loaddiv">
                                {loading ? (
                                    <button className="btn btn-success" type="button" disabled>
                                    <span className="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                                    &nbsp; LOADING...
                                    </button>
                                ) : (
                                    <button className="btn btn-success">PLACE ORDER</button>
                                )}
                                </div>
                            )
                        }
                    </div>
                </div>
            </div>;
        </Layout>
    )
}

export default Cart;