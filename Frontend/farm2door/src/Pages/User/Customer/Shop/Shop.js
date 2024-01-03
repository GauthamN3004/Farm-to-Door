import Layout from "../../../../Component/Layout/Layout";
import { useAuth } from "../../../../Context/AuthContext";
import { toast } from 'react-hot-toast';
import FarmerPanel from "../../../../Component/Layout/FarmerPanel/FarmerPanel";
import HarvestCard from "../../../../Component/HarvestCard/HarvestCard";
import { useEffect, useState } from "react";
import "./Shop.css"
import axios from "axios";
import CustomerPanel from "../../../../Component/Layout/CustomerPanel/CustomerPanel";

function Shop() {
    const {isLoggedIn, userData, login, logout} = useAuth();
    const [page, setPage] = useState(1);
    const [loading, setLoading] = useState(false);
    const [harvests, setHarvests] = useState([]);

    const authHeader = {
        username: userData.username,
        password: userData.raw_password,
    };

    const fetchHarvests = async (pageNumber) => {
        try {
            setLoading(true);
            const response = await axios.get(`http://localhost:8080/api/customer/${userData.userId}/shop?page=${pageNumber}&pageSize=9`);
            setHarvests((prevHarvests) => [...prevHarvests, ...response.data]);
            setLoading(false);
        } catch (error) {
            console.error('Error fetching harvests:', error);
            setLoading(false);
        }
    };

    const handleLoadMore = async () => {
        const nextPage = page + 1;
        await fetchHarvests(nextPage);
        setPage(nextPage);
    };

    const addToCart = async (harvestId, quantity) => {
        const data = {
            customerId: userData.userId,
            harvestId: harvestId,
            quantity: quantity
        };
        try{
            const response = await axios.post(`http://localhost:8080/api/customer/${userData.userId}/cart`, data, {auth: authHeader});
            if(response.status == 200){
                toast.success("Item Added to Cart.");
                setHarvests((prevHarvests) => {
                    return prevHarvests.map((harvest) => {
                        if (harvest.harvestId === harvestId) {
                            return {
                                ...harvest,
                                quantity: harvest.quantity - quantity
                            };
                        }
                        return harvest;
                    });
                });
            }
            else{
                toast.error("Could not add item to cart!");
            }
        } catch (error){
            toast.error("Could not add item to cart!");
        }
    }
    
    useEffect(() => {
        console.log("UseEffect");
        fetchHarvests(page);
    }, [isLoggedIn, userData]);

    return (
        <Layout>
            <div className="container">
                <div className="row">
                    <div className="col-sm-3">
                        <CustomerPanel activeTab={"shop"} />
                    </div>
                    <div className="col-sm-9">
                        <div>
                            <div className="row myHarvest">
                                {harvests.map((harvestData) => (
                                    <div className="col-md-4">
                                        <HarvestCard harvestData={harvestData} userRole={userData.role} eventHandle={addToCart}/>
                                </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </div>;
        </Layout>
    )
}

export default Shop;