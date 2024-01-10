import "./CustomerOrderItemCard.css";
import { Link } from 'react-router-dom';
import { Button, Modal } from 'antd';
import { useState } from "react";

function CustomerOrderItemCard({orderItemData}){
    const [isCancelOrderModalOpen, setCancelOrderModalOpen] = useState(false);

    function showCancelOrderModal(){

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
                    <Button danger onClick={() => {setCancelOrderModalOpen(true)}}>
                        CANCEL
                    </Button>
                    <Modal cancelText= "No" okText="Yes" open={isCancelOrderModalOpen} onOk={() => {console.log('ok');}} onCancel={() => {setCancelOrderModalOpen(false)}}>
                        <p>Are you sure?</p>
                    </Modal>
                </div>  
            </div>
        </div>
    )
}

export default CustomerOrderItemCard;