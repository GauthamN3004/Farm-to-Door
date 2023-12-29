import Layout from "../../Component/Layout/Layout";
import "./PageNotFound.css"

function PageNotFound(){
    return (
        <Layout>
            <div className="pageNotFound">
                <h1>404 - Page Not Found</h1>
                <p>Oops! The page you are looking for might be in another garden.</p>
            </div>
        </Layout>
    )
}

export default PageNotFound;