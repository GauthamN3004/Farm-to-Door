import "./CartCard.css"
import { Button, Modal } from 'antd';
import { useState } from "react";
import { toast } from "react-hot-toast";

function CartCard ({cartData, userRole, eventHandle}) {
    const [isDeleteModalOpen, setDeleteModalOpen] = useState(false);
    

    const showDeleteModal = () => {
        setDeleteModalOpen(true);
    };

    const handleDeleteOk = () => {
        async function handleDelete() {
            try {
                await eventHandle(cartData.cartId);
                setDeleteModalOpen(false);
            } catch (error) {
                console.error("Error deleting cart item:", error);
            }
        }

        handleDelete();
    }

    const handleDeleteCancel = () => {
        setDeleteModalOpen(false);
    };

    // const handleQuantityChange = (e) => {
    //     setCartQuantityText(e.target.value);
    //     setCartQuantity(Number(e.target.value));
    // }

    // const handleAddToCart = async () => {
    //     if(cartQuantity > harvestData.quantity){
    //         toast.error(`Available Quantity is ${harvestData.quantity} ${harvestData.units}`)
    //     }
    //     else if(cartQuantity < harvestData.smallestUnitSize){
    //         toast.error(`Minimum Quantity: ${harvestData.smallestUnitSize} ${harvestData.units}`)
    //     }
    //     else if((cartQuantity % harvestData.smallestUnitSize) !== 0){
    //         toast.error(`Must be a multiple of: ${harvestData.smallestUnitSize} ${harvestData.units}`)
    //     }
    //     else {
    //         await eventHandle(harvestData.harvestId, cartQuantity);
    //         setCartQuantity(harvestData.smallestUnitSize);
    //         setCartModalOpen(false);
    //     }
    // }

    // const handleCartCancel = () => {
    //     setCartModalOpen(false);
    //     setCartQuantity(harvestData.smallestUnitSize);
    // }

    return (
        <div className="card">
            <img className="card-img-top" src={cartData.harvest.imageUrl} alt="Crop Image" />
            <div className="card-body">
                <h5 className="card-title">{cartData.harvest.cropName}</h5>
                <p className="card-text"><b>Qty:</b> {cartData.quantity} {cartData.harvest.units}</p>
                <p><b>Seller:</b> <a href="#">{cartData.harvest.farmer.firstname} {cartData.harvest.farmer.lastname}</a> </p>
                <p><b>Price:</b> ₹ {cartData.harvest.pricePerQuantity * cartData.quantity} </p>
                
                <>
                    <Button danger onClick={showDeleteModal}>
                        REMOVE FROM CART
                    </Button>
                    <Modal cancelText= "No" okText="Yes" open={isDeleteModalOpen} onOk={handleDeleteOk} onCancel={handleDeleteCancel}>
                        <p>Are you sure?</p>
                    </Modal>
                </>              
            </div>
        </div>
    )
}

export default CartCard;