import FarmerPanel from "../../../../Component/Layout/FarmerPanel/FarmerPanel";
import Layout from "../../../../Component/Layout/Layout";
import "./FarmerOrders.css";

function FarmerOrders() {
    return (
        <Layout>
            <div className="container">
                <div className="row">
                    <div className="col-sm-3">
                        <FarmerPanel activeTab={"orders"} />
                    </div>

                    <div className="col-sm-9">
                        
                    </div>
                </div>
            </div>;
        </Layout>
    )
}

export default FarmerOrders;