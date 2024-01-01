import Layout from "../../Component/Layout/Layout";
import Content from "../../Component/Layout/Content/Content";
import Content2 from "../../Component/Layout/Content/Content2";
import { useAuth } from "../../Context/AuthContext";

function HomePage(){
    const {isLoggedIn, userData, login, logout} = useAuth();
    const commitment = [
        {'title': 'Quality Assurance', 'content':'We work closely with local farmers who share our commitment to sustainable and ethical farming practices. Our team ensures that every product meets our stringent quality standards.'},
        {'title':'Locally Sourced', 'content': 'By choosing Farm2Door, you support local agriculture and communities. Our produce is sourced from nearby farms, reducing the carbon footprint and promoting regional economic growth.'},
        {'title':'Freshness Guaranteed', 'content': 'We understand the importance of freshness in every bite. Our streamlined supply chain ensures that your order reaches you at the peak of its freshness, preserving nutritional value and flavor.'}
    ]
    return (
        <Layout>
            <Content 
                    heading={"WELCOME TO FARM-TO-DOOR"}
                    className="green"
                    content="At Farm2Door, we believe in bringing the freshness of the farm directly to your doorstep. Our mission is to provide you with high-quality, locally sourced produce and products, ensuring a connection between farmers and consumers."
            />

            <Content2 
                heading="OUR COMMITMENT"
                className="white"
                list={commitment}
            />
        </Layout>
    )
}

export default HomePage;