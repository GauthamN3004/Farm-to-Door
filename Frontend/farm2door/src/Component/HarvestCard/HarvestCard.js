import "./HarvestCard.css"
import { Button, Modal } from 'antd';
import { useState } from "react";
import { toast } from "react-hot-toast";

function HarvestCard ({harvestData, userRole, eventHandle}) {

    const [isDeleteModalOpen, setDeleteModalOpen] = useState(false);
    const [isCartModalOpen, setCartModalOpen] = useState(false);
    const [cartQuantityText, setCartQuantityText] = useState(harvestData.smallestUnitSize);
    const [cartQuantity, setCartQuantity] = useState(harvestData.smallestUnitSize);
    const [cropName, setCropName] = useState(harvestData.cropName);
    const [harvestDate, setHarvestDate] = useState(new Date(harvestData.harvestDate).toISOString().substring(0, 10));
    const [expiryDate, setExpiryDate] = useState(new Date(harvestData.expiryDate).toISOString().substring(0, 10));
    const [quantity, setQuantity] = useState(harvestData.quantity);
    const [unit, setUnit] = useState(harvestData.units);
    const [price, setPrice] = useState(harvestData.pricePerQuantity);
    const [smallestUnitSize, setSmallestUnitSize] = useState(harvestData.smallestUnitSize);
    const [file, setFile] = useState(null);
    const [imageUrl, setImageUrl] = useState(null);
    

    const showDeleteModal = () => {
        setDeleteModalOpen(true);
    };

    const handleDeleteOk = () => {
        async function handleDelete() {
            try {
                await eventHandle(harvestData.harvestId);
                setDeleteModalOpen(false);
            } catch (error) {
                console.error("Error deleting harvest:", error);
            }
        }

        handleDelete();
    }

    const showCartModal = () => {
        setCartModalOpen(true);
    };

    const handleDeleteCancel = () => {
        setDeleteModalOpen(false);
    };

    const handleQuantityChange = (e) => {
        setCartQuantityText(e.target.value);
        setCartQuantity(Number(e.target.value));
    }

    const handleAddToCart = async () => {
        if(cartQuantity > harvestData.quantity){
            toast.error(`Available Quantity is ${harvestData.quantity} ${harvestData.units}`)
        }
        else if(cartQuantity < harvestData.smallestUnitSize){
            toast.error(`Minimum Quantity: ${harvestData.smallestUnitSize} ${harvestData.units}`)
        }
        else if((cartQuantity % harvestData.smallestUnitSize) !== 0){
            toast.error(`Must be a multiple of: ${harvestData.smallestUnitSize} ${harvestData.units}`)
        }
        else {
            await eventHandle(harvestData.harvestId, cartQuantity);
            setCartQuantity(harvestData.smallestUnitSize);
            setCartModalOpen(false);
        }
    }

    const handleCartCancel = () => {
        setCartModalOpen(false);
        setCartQuantity(harvestData.smallestUnitSize);
    }

    return (
        <div className="card">
            <img className="card-img-top" src={harvestData.imageUrl} alt="Crop Image" />
            <div className="card-body">
                <h5 className="card-title">{harvestData.cropName}</h5>
                <p className="card-text">₹{harvestData.pricePerQuantity}/{harvestData.units}</p>
                <p className="card-text"><b>Avail. Qty:</b> {harvestData.quantity} {harvestData.units}</p>
                {userRole == "ROLE_FARMER" && (
                    <>
                        <Button danger onClick={showDeleteModal}>
                            DELETE
                        </Button>
                        <Modal cancelText= "No" okText="Yes" open={isDeleteModalOpen} onOk={handleDeleteOk} onCancel={handleDeleteCancel}>
                            <p>Are you sure?</p>
                        </Modal>
                    </>
                )}
                {userRole == "ROLE_CUSTOMER" && (
                    <>
                        <Button onClick={showCartModal}>
                            ADD TO CART
                        </Button>
                        <Modal cancelText= "Cancel" okText="Add to Cart" open={isCartModalOpen} onOk={handleAddToCart} onCancel={handleCartCancel}>
                            QUANTITY (in {harvestData.units}): <input className="modalForm" type="number" value={cartQuantityText} onChange={handleQuantityChange}></input> 
                        </Modal>
                    </>
                )}
                
            </div>
        </div>
    )
}

export default HarvestCard;