import Layout from "../../../../Component/Layout/Layout";
import { useAuth } from "../../../../Context/AuthContext";
import { toast } from 'react-hot-toast';
import HarvestCard from "../../../../Component/HarvestCard/HarvestCard";
import { useEffect, useState } from "react";
import "./Shop.css"
import axios from "axios";
import CustomerPanel from "../../../../Component/Layout/CustomerPanel/CustomerPanel";
import FilterPanel from "../../../../Component/FilterPanel/FilterPanel";
import { Pagination } from 'antd';


function Shop() {
    const {isLoggedIn, userData} = useAuth();
    const [page, setPage] = useState(1);
    const [loading, setLoading] = useState(false);
    const [harvests, setHarvests] = useState([]);
    const [categoryList, setCategoryList] = useState(null);
    const [priceRange, setPriceRange] = useState([0, 1000]);
    // const [resetHarvest, setResetHarvest] = useState(true);

    const authHeader = {
        username: userData.username,
        password: userData.raw_password,
    };

    const fetchHarvests = async (pageNumber, resetHarvest) => {
        var additional_paramters = '';
        if(categoryList !== null){
            additional_paramters += `&category=${categoryList}`;
        }
        const apiEndPoint = `http://localhost:8080/api/customer/${userData.userId}/shop?page=${pageNumber}&pageSize=25&minPrice=${priceRange[0] || 0}&maxPrice=${priceRange[1] || 1000}${additional_paramters}`;
        
        try {
            setLoading(true);
            const response = await axios.get(apiEndPoint);
            if(resetHarvest){
                setHarvests(response.data);
            }
            else{
                setHarvests((prevHarvests) => [...prevHarvests, ...response.data]);
            }
            setLoading(false);
        } catch (error) {
            console.error('Error fetching harvests:', error);
            setLoading(false);
        }
    };

    const handleLoadMore = async () => {
        const nextPage = page + 1;
        await fetchHarvests(nextPage, false);
        setPage(nextPage);
    };

    const handleFilterChange = async (categories, priceRange) => {
        setCategoryList(categories);
        setPriceRange(priceRange);
        setPage(1);
    }

    const addToCart = async (harvestId, quantity) => {
        const data = {
            customerId: userData.userId,
            harvestId: harvestId,
            quantity: quantity
        };
        try{
            const response = await axios.post(`http://localhost:8080/api/customer/${userData.userId}/cart`, data, {auth: authHeader});
            if(response.status === 200){
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
        fetchHarvests(page, true);
    }, []);

    useEffect(() => {
        setPage(1);
        fetchHarvests(1, true);
    }, [categoryList, priceRange]);

    return (
        <Layout>
            <div className="container">
                <div className="row">
                    <div className="col-sm-3">
                        <CustomerPanel activeTab={"shop"} />
                        <br></br>
                        <FilterPanel handleFilterChange={handleFilterChange}/>
                    </div>
                    <div className="col-sm-9">
                    <div className="row myHarvest">
                        {harvests.length > 0 ? (
                            harvests.map((harvestData) => (
                            <div className="col-md-4" key={harvestData.id}>
                                <HarvestCard harvestData={harvestData} userRole={userData.role} eventHandle={addToCart} />
                            </div>
                            ))
                        ) : (
                            <h4>No Matching Products to display</h4>
                        )}
                    </div>
                        <div className="loaddiv">{(loading) ? <button className="btn btn-success" type="button" disabled>
                                <span className="spinner-border spinner-border-sm " role="status" aria-hidden="true"></span>
                                &nbsp; LOADING...
                            </button> :
                            <button className="btn btn-success" onClick={handleLoadMore}>LOAD MORE</button>
                        }
                        </div>
                    </div>
                </div>
            </div>;
        </Layout>
    )
}

export default Shop;