import "../User.css"
import { useState , React } from "react";
import { useAuth } from "../../../Context/AuthContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import toast from 'react-hot-toast';
import Layout from "../../../Component/Layout/Layout";


function FarmerAddHarvest() {
    const {isLoggedIn, userData, login, logout} = useAuth();
    const [cropName, setCropName] = useState('');
    const [harvestDate, setHarvestDate] = useState('');
    const [expiryDate, setExpiryDate] = useState('');
    const [quantity, setQuantity] = useState('');
    const [unit, setUnit] = useState('');
    const [price, setPrice] = useState('');
    const [smallestUnitSize, setSmallestUnitSize] = useState('');
    const [file, setFile] = useState(null);
    const [imageUrl, setImageUrl] = useState(null);
    const navigate = useNavigate();

    // const basicAuthHeader = `Basic ${btoa(`${yourUsername}:${yourPassword}`)}`;
    const uploadFile = async () => {
        try {
            const formData = new FormData();
            formData.append('file', file);
    
            const response = await axios.post('http://localhost:8080/api/files/upload', formData);
    
            if (response.status != 200) {
                throw new Error('File upload failed');
            }

            return response.data;
        } catch (error) {
            toast.error("Image Upload Failed !");
            throw new Error('File upload failed');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (new Date(expiryDate) < new Date(harvestDate)){
            toast.error("Expiry Date cannot be before Harvest Date");
        }
        else if(quantity < smallestUnitSize){
            toast.error("Quantity cannot be less than the smallest unit size");
        }
        
        else {
            try {
                const imageUrl = await uploadFile();
                setImageUrl(imageUrl);
                const data = {
                    "cropName": cropName,
                    "harvestDate": harvestDate,
                    "expiryDate": expiryDate,
                    "quantity": quantity,
                    "units": unit,
                    "pricePerQuantity": price,
                    "smallestUnitSize": smallestUnitSize,
                    "imageUrl": 'https://farm-to-door.s3.ap-south-1.amazonaws.com/' + imageUrl
                }

                const authHeader = {
                    username: userData.username,
                    password: userData.raw_password,
                };

                const response = await axios.post('http://localhost:8080/api/farmer/'+userData.userId+'/harvest', data, {auth: authHeader});
                if (response.status != 200) {
                    throw new Error('Data upload failed');
                }
                
                toast.success("Harvest Uploaded Successfully!");
                setCropName('');
                setHarvestDate('');
                setExpiryDate('');
                setQuantity('');
                setUnit('');
                setPrice('');
                setFile(null);
                setSmallestUnitSize('');
                setImageUrl(null);
            } catch (error) {
                toast.error("Upload Failed - Error", error);
                console.error('Data upload failed', error);
            }
        }

        setCropName('');
        setHarvestDate('');
        setExpiryDate('');
        setQuantity('');
        setUnit('');
        setPrice('');
    };


    return (
        <Layout>
            <div className="authForm loginForm">
                <h2>ADD HARVEST</h2>
                <form onSubmit={handleSubmit} method="POST">
                    <div className="form-group">
                        <label htmlFor="cropName"><b>CROP NAME</b></label>
                        <input type="text" className="form-control" name="cropName" value={cropName} placeholder="Enter Crop Name" required onChange={(e) => setCropName(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="harvestDate"><b>HARVEST DATE</b></label>
                        <input type="date" className="form-control" name="harvestDate" value={harvestDate} placeholder="Enter Harvest Date" required onChange={(e) => setHarvestDate(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="expiryDate"><b>EXPIRY DATE</b></label>
                        <input type="date" className="form-control" name="expiryDate" value={expiryDate} placeholder="Enter Expiry Date" required onChange={(e) => setExpiryDate(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantity"><b>QUANTITY</b></label>
                        <input type="number" className="form-control" name="quantity" value={quantity} placeholder="Enter Warehouse Quantity" required onChange={(e) => setQuantity(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="unit"><b>UNIT</b></label>
                        <select className="form-control" name="unit" value={unit} onChange={(e) => setUnit(e.target.value)} required>
                            <option value="">Select Unit</option>
                            <option value="Kilograms (kg)">Kilograms (kg)</option>
                            <option value="Grams (g)">Grams (g)</option>
                            <option value="Units">Units</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="price"><b>PRICE PER QUANTITY</b></label>
                        <input type="number" className="form-control" name="price" value={price} placeholder="Enter Price per Quantity" required onChange={(e) => setPrice(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="smallestUnitSize"><b>SMALLEST UNIT SIZE</b></label>
                        <input type="number" className="form-control" name="smallestUnitSize" value={smallestUnitSize} placeholder="Enter Smallest Unit Size" required onChange={(e) => setSmallestUnitSize(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="image"><b>HARVEST IMAGE</b></label>
                        <input type="file" className="form-control" id="image" accept=".jpeg, .jpg, .png" required onChange={(e) => setFile(e.target.files[0])}/>
                    </div>
                    <br />
                    <button type="submit" className="btn btn-primary">SUBMIT</button>
                </form>
            </div>
        </Layout>
    )
}

export default FarmerAddHarvest;