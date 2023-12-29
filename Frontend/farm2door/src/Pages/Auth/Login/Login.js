import {React, useState} from "react";
import Layout from "../../../Component/Layout/Layout";
import "../Auth.css"
import axios from "axios";
import toast from 'react-hot-toast';
import { useNavigate } from "react-router-dom";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = {
            "username": username,
            "password": password
        };
        try{
            const res = await axios.post(
                "http://localhost:8080/auth/login",
                data,
                {
                    validateStatus: function (status) {
                        return status >= 200 && status < 500;
                    },
                }
            );

            if(res.status == 200){
                toast.success(res.data.userId + " " + res.data.username + "Logged In");
                setTimeout(() => {
                    navigate("/");
                }, 10);
            }
            else if (res.status <= 450) {
                toast.error(res.data);
            }
        } catch(error) {
            toast.error("Something went wrong !");
        }

        
    };


    return (
        <Layout>
            <div className="authForm loginForm">
                <h2>LOGIN</h2>
                <form onSubmit={handleSubmit} method="POST">
                    <div className="form-group">
                        {/* <label htmlFor="username"><b>USERNAME</b></label> */}
                        <input type="text" className="form-control" name="username" value={username} placeholder="Enter Username" required onChange={(e) => setUsername(e.target.value)}/>
                    </div>
                    <div className="form-group">
                        {/* <label htmlFor="password"><b>PASSWORD</b></label> */}
                        <input type="password" className="form-control" id="password" value={password} placeholder="Enter Password" required onChange={(e) => setPassword(e.target.value)}/>
                    </div>
                    <br />
                    <button type="submit" className="btn btn-primary">SUBMIT</button>
                </form>
            </div>
        </Layout>
    )
}

export default Login;