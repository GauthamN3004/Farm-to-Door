import "./CustomerOrderCard.css"
import CustomerOrderItemCard from "./CustomerOrderItemCard";

function CustomerOrderCard({orderData}){
    const originalDate = new Date(orderData.orderDate);
    originalDate.setDate(originalDate.getDate() + 1);
    const formattedDateString = originalDate.toISOString().split('T')[0];

    return (
        <div className="card">
            <div className="card-header orderInfo">
                <div className="orderDetail">
                    <div className="small-heading">ORDER DATE</div>
                    <div className="data">{formattedDateString}</div>
                </div>
                <div className="orderDetail">
                    <div className="small-heading">TOTAL</div>
                    <div className="data order-value">₹ {orderData.totalPrice}</div>
                </div>
            </div>
            <ul className="list-group list-group-flush">
                {orderData.orderItems.map((item) => 
                    <li className="list-group-item">
                        <CustomerOrderItemCard orderItemData = {item} />
                    </li>
                )}
            </ul>
        </div>
    )
}

export default CustomerOrderCard;
