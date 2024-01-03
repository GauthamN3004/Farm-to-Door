import Layout from "../../../../Component/Layout/Layout";
import { useAuth } from "../../../../Context/AuthContext";
import { toast } from 'react-hot-toast';
import FarmerPanel from "../../../../Component/Layout/FarmerPanel/FarmerPanel";
import HarvestCard from "../../../../Component/HarvestCard/HarvestCard";
import { useEffect, useState } from "react";
import "./MyHarvest.css"
import axios from "axios";

function MyHarvest() {
    const {isLoggedIn, userData, login, logout} = useAuth();
    const [page, setPage] = useState(1);
    const [loading, setLoading] = useState(false);
    const [harvests, setHarvests] = useState([]);

    const fetchHarvests = async (pageNumber) => {
        try {
            setLoading(true);
            const response = await axios.get(`http://localhost:8080/api/farmer/${userData.userId}/harvest?page=${pageNumber}&pageSize=9`);
            setHarvests((prevHarvests) => [...prevHarvests, ...response.data]);
            setLoading(false);
        } catch (error) {
            console.error('Error fetching harvests:', error);
            setLoading(false);
        }
    };

    const deleteHarvest = async (harvestId) => {
        const authHeader = {
            username: userData.username,
            password: userData.raw_password,
        };
        try{
            const response = await axios.delete(`http://localhost:8080/api/farmer/${userData.userId}/harvest/${harvestId}`, {auth: authHeader});
            if(response.status == 200){
                setHarvests((prevHarvests) => prevHarvests.filter((harvest) => harvest.harvestId !== harvestId));
                toast.success("Harvest Deleted Successfully");
            } else {
                console.log("Error");
            }
        } catch(error){
            toast.error("Could not delete the harvest!");
        }
    }
    
    const handleLoadMore = async () => {
        const nextPage = page + 1;
        await fetchHarvests(nextPage);
        setPage(nextPage);
    };
    
    // useEffect to fetch harvests when the component mounts
    useEffect(() => {
        console.log("UseEffect");
        fetchHarvests(page);
    }, [isLoggedIn, userData]);
    

    return (
        <Layout>
            <div className="container">
                <h3>DASHBOARD</h3>
                <div className="row">
                    <div className="col-sm-3">
                    <FarmerPanel activeTab={"my-harvest"} />
                    </div>
                    <div className="col-sm-9">
                        <div className="row myHarvest">
                            {harvests.map((harvestData) => (
                                <div className="col-md-4">
                                    <HarvestCard harvestData={harvestData} userRole={userData.role} eventHandle={deleteHarvest}/>
                            </div>
                            ))}
                        </div>
                        <div className="loaddiv">{(loading) ? <button class="btn btn-success" type="button" disabled>
                                <span class="spinner-border spinner-border-sm " role="status" aria-hidden="true"></span>
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

export default MyHarvest;