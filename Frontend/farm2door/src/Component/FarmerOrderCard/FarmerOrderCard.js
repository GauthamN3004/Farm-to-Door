import { Button, Modal } from "antd";
import { Link } from "react-router-dom";
import "./FarmerOrderCard.css"
import { useState } from "react";

function FarmerOrderCard({orderData, updateOrderStatus}){
    const [isCancelOrderModalOpen, setCancelOrderModalOpen] = useState(false);
    const [isFulfillOrderModalOpen, setFulfillOrderModalOpen] = useState(false);

    const originalDate = new Date(orderData.order.orderDate);
    originalDate.setDate(originalDate.getDate() + 1);
    const formattedDateString = originalDate.toISOString().split('T')[0];

    const handleFulfillOrderItem = (lineItemId) => {
        updateOrderStatus(lineItemId, 2);
        setFulfillOrderModalOpen(false);
    }

    const handleCancelOrderItem = (lineItemId) => {
        updateOrderStatus(lineItemId, 4);
        setCancelOrderModalOpen(false);
    }

    return (
        <div className="itemInfo">
            <div className=" column imgDiv">
                <img className="itemImage" src={orderData.harvest.imageUrl}></img>
            </div>
            <div className="column">
                <div className="productHeading"><Link >{orderData.harvest.cropName}</Link></div>
                <div><b>ORDER DATE:</b> {formattedDateString}</div>
                <div className="seller"><b>BUYER:</b> <Link>{orderData.order.customer.firstname} {orderData.order.customer.lastname}</Link></div>
                <div><b>QUANTITY:</b> {orderData.quantity} {orderData.harvest.units}</div>
                <div><b>PRICE:</b> ₹{orderData.price}</div>
                <div><b>ADDRESS:</b> {orderData.order.customer.address}, {orderData.order.customer.city}</div>
            </div>
            <div className="column align-right">
                <div><b>STATUS:</b> {orderData.orderStatus.farmerStatus}</div>
                {(orderData.orderStatus.statusId == 1) ? 
                    <div>
                        <button className="btn btn-outline-success" onClick={() => {setFulfillOrderModalOpen(true)}}>FULFILL</button>
                        <Modal cancelText= "No" okText="Yes" open={isFulfillOrderModalOpen} onOk={() => {handleFulfillOrderItem(orderData.lineItemId)}} onCancel={() => {setFulfillOrderModalOpen(false)}}>
                            <p>Are you sure?</p>
                        </Modal>
                    </div> : <></>
                }
                {(orderData.orderStatus.statusId == 1) ?
                    <div>
                        <button className="btn btn-outline-danger" onClick={() => {setCancelOrderModalOpen(true)}}>CANCEL</button>
                        <Modal cancelText= "No" okText="Yes" open={isCancelOrderModalOpen} onOk={() => {handleCancelOrderItem(orderData.lineItemId)}} onCancel={() => {setCancelOrderModalOpen(false)}}>
                            <p>Are you sure?</p>
                        </Modal>
                    </div> : <></>
                }               
            </div>
        </div>
    )
}

export default FarmerOrderCard;