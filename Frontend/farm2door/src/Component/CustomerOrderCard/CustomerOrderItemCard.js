import "./CustomerOrderItemCard.css";
import { Link } from 'react-router-dom';
import { Button, Modal } from 'antd';
import { useState } from "react";

function CustomerOrderItemCard({orderItemData, updateOrderStatus}){
    const [isCancelOrderModalOpen, setCancelOrderModalOpen] = useState(false);
    const [isDeliveredModalOpen, setDeliveredModalOpen] = useState(false);

    function handleDelivered(lineItemId){
        setDeliveredModalOpen(false);
        updateOrderStatus(lineItemId, 6);
    }


    function handleCancel(lineItemId){
        setCancelOrderModalOpen(false);
        updateOrderStatus(lineItemId, 3);
    }

    return (
        <div className="itemInfo">
            <div className="column imgDiv">
                <img className="itemImage" src={orderItemData.harvest.imageUrl}></img>
            </div>
            <div className="column">
                <div className="productHeading"><Link >{orderItemData.harvest.cropName}</Link></div>
                <div className="seller"><b>SELLER:</b> <Link>{orderItemData.harvest.farmer.firstname} {orderItemData.harvest.farmer.lastname}</Link></div>
                <div><b>QUANTITY:</b> {orderItemData.quantity} {orderItemData.harvest.units}</div>
                <div><b>PRICE:</b> ₹{orderItemData.price}</div>
            </div>
            <div className="column align-right">
                <div><b>STATUS:</b> {orderItemData.orderStatus.customerStatus}</div>
                <br></br>
                <div>
                    {(orderItemData.orderStatus.statusId == 1) ? <Button danger onClick={() => {setCancelOrderModalOpen(true)}}>
                        CANCEL
                    </Button> : <></>}
                    <Modal cancelText= "No" okText="Yes" open={isCancelOrderModalOpen} onOk={() => {handleCancel(orderItemData.lineItemId)}} onCancel={() => {setCancelOrderModalOpen(false)}}>
                        <p>Are you sure?</p>
                    </Modal>
                </div>
                <div>
                    {(orderItemData.orderStatus.statusId == 2) ? <Button onClick={() => {setDeliveredModalOpen(true)}}>
                        DELIVERED
                    </Button> : <></>}
                    <Modal cancelText= "No" okText="Yes" open={isDeliveredModalOpen} onOk={() => {handleDelivered(orderItemData.lineItemId)}} onCancel={() => {setDeliveredModalOpen(false)}}>
                        <p>Are you sure?</p>
                    </Modal>
                </div> 
            </div>
        </div>
    )
}

export default CustomerOrderItemCard;