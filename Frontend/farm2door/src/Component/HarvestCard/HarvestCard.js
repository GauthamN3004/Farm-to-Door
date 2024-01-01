import "./HarvestCard.css"
import { Button, Modal } from 'antd';
import { useState } from "react";

function HarvestCard ({harvestData, deleteHarvest}) {

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [cropName, setCropName] = useState(harvestData.cropName);
    const [harvestDate, setHarvestDate] = useState(new Date(harvestData.harvestDate).toISOString().substring(0, 10));
    const [expiryDate, setExpiryDate] = useState(new Date(harvestData.expiryDate).toISOString().substring(0, 10));
    const [quantity, setQuantity] = useState(harvestData.quantity);
    const [unit, setUnit] = useState(harvestData.units);
    const [price, setPrice] = useState(harvestData.pricePerQuantity);
    const [smallestUnitSize, setSmallestUnitSize] = useState(harvestData.smallestUnitSize);
    const [file, setFile] = useState(null);
    const [imageUrl, setImageUrl] = useState(null);
    

    const showModal = () => {
        setIsModalOpen(true);
    };
    const handleOk = () => {
        async function handleDelete() {
            try {
                await deleteHarvest(harvestData.harvestId);
                setIsModalOpen(false);
            } catch (error) {
                console.error("Error deleting harvest:", error);
            }
        }

        handleDelete();
    }

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    return (
        <div className="card">
            <img className="card-img-top" src={harvestData.imageUrl} alt="Crop Image" />
            <div className="card-body">
                <h5 className="card-title">{harvestData.cropName}</h5>
                <p className="card-text">₹{harvestData.pricePerQuantity}/{harvestData.units}</p>
                <p className="card-text"><b>Avail. Qty:</b> {harvestData.quantity} {harvestData.units}</p>
                <Button danger onClick={showModal}>
                    DELETE
                </Button>
                <Modal cancelText= "No" okText="Yes" open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                    <p>Are you sure?</p>
                </Modal>
            </div>
        </div>
    )
}

export default HarvestCard;