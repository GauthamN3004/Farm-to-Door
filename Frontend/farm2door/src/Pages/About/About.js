import Layout from "../../Component/Layout/Layout";
import Content from "../../Component/Layout/Content/Content";
import Content2 from "../../Component/Layout/Content/Content2";

function About(){
    const mission = [
        {
        title: "Empowering Local Agriculture",
        content:
            "At the core of Farm2Door is a mission to empower local farmers and promote sustainable agricultural practices. We believe that by supporting local agriculture, we contribute to the overall health of our communities.",
        },
        {
        title: "Ensuring Freshness",
        content:
            "We are dedicated to bringing you the freshest produce straight from the farm. Our team works tirelessly to ensure that every item in your order reflects the peak of its flavor and nutritional value.",
        },
        {
        title: "Building Community",
        content:
            "Farm2Door is more than a marketplace; it's a community. We strive to foster a sense of connection between farmers and consumers, creating a platform where stories are shared, and relationships are cultivated.",
        },
    ]

    const values = [
        {
        title: "Sustainability",
        content:
            "We prioritize eco-friendly practices, striving to minimize our environmental impact. From reducing waste to supporting sustainable farming methods, our commitment to the planet is unwavering.",
        },
        {
        title: "Transparency",
        content:
            "We believe in transparency at every step of the journey. Know where your food comes from, who grows it, and the impact of your choices.",
        },
        {
        title: "Community Engagement",
        content:
            "Building a strong community is at the heart of our mission. Through events, partnerships, and educational initiatives, we aim to engage and connect with our customers.",
        },
    ]
    return (
        <Layout>
            <Content 
                    heading={"OUR STORY"}
                    className="green"
                    content="Welcome to Farm-to-Door, where our journey begins with a passion for connecting people with the goodness of the land. Founded with a vision to bridge the gap between local farmers and discerning consumers, Farm2Door is more than just an online marketplace—it's a commitment to sustainability, quality, and community."
            />

            <Content2 
                heading="OUR MISSION"
                className="white"
                list={mission}
            />

            <Content2 
                heading="OUR VALUES"
                className="green"
                list={values}
            />
        </Layout>
    )
}

export default About;