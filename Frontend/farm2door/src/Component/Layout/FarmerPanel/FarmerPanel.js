import "./FarmerPanel.css"
import { NavLink } from "react-router-dom";

function FarmerPanel({activeTab}) {
    return (
        <ul className="list-group">
        <NavLink to="/farmer/my-harvest"> <li className={`list-group-item ${activeTab === "my-harvest" && "myActive"}`}>MY HARVEST</li> </NavLink>
        <NavLink to="/farmer/add-harvest"> <li className={`list-group-item ${activeTab === "add-harvest" && "myActive"}`}>ADD HARVEST</li> </NavLink>
        <NavLink to="/farmer/orders"> <li className={`list-group-item ${activeTab === "orders" && "myActive"}`}>MY ORDERS</li> </NavLink>
        </ul>
    )
}

export default FarmerPanel;