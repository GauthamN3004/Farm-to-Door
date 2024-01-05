import "./CustomerPanel.css"
import { NavLink } from "react-router-dom";

function CustomerPanel({activeTab}) {
    return (
        <ul className="list-group">
        <NavLink to="/customer/shop"> <li className={`list-group-item ${activeTab === "shop" && "myActive"}`}>SHOP</li> </NavLink>
        <NavLink to="/customer/cart"> <li className={`list-group-item ${activeTab === "cart" && "myActive"}`}>CART</li> </NavLink>
        {/* <NavLink to="/user/farmer/add-harvest"> <li className={`list-group-item ${activeTab === "add-harvest" && "myActive"}`}>ADD HARVEST</li> </NavLink> */}
        </ul>
    )
}

export default CustomerPanel;