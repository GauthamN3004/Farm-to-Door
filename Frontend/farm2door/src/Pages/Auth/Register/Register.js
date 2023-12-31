import {React, useState} from "react";
import Layout from "../../../Component/Layout/Layout";
import { useNavigate } from "react-router-dom";
import "../Auth.css"
import axios from "axios";
import toast from 'react-hot-toast';

function Register() {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [userType, setUserType] = useState("");
    const [email, setEmail] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = {
            "username": username,
            "firstname": firstName,
            "lastname": lastName,
            "password": password,
            "userType": userType,
            "email": email,
            "address": address,
            "city": city,
            "phno": phoneNumber
        };
        try{
            const res = await axios.post(
                "http://localhost:8080/auth/register",
                data,
                {
                    validateStatus: function (status) {
                        return status >= 200 && status < 500;
                    },
                }
            );

            if(res.status == 200){
                toast.success(res.data);
                setTimeout(() => {
                    navigate("/login");
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
            <div className="authForm registerForm">
                <h2>REGISTER USER</h2>
                <form onSubmit={handleSubmit} method="POST">
                    <div className="form-group">
                        {/* <label htmlFor="firstName"><b>FIRSTNAME</b></label> */}
                        <input type="text" className="form-control" name="firstName" value={firstName} placeholder="Enter First Name" required onChange={(e) => setFirstName(e.target.value)} />
                    </div>
                    <div className="form-group">
                        {/* <label htmlFor="lastName"><b>LASTNAME</b></label> */}
                        <input type="text" className="form-control" name="lastName" value={lastName} placeholder="Enter Last Name" required onChange={(e) => setLastName(e.target.value)}/>
                    </div>
                    <div className="form-group">
                        {/* <label htmlFor="username"><b>USERNAME</b></label> */}
                        <input type="text" className="form-control" name="username" value={username} placeholder="Enter Username" required onChange={(e) => setUsername(e.target.value)}/>
                    </div>
                    <div className="form-group">
                        {/* <label htmlFor="password"><b>PASSWORD</b></label> */}
                        <input type="password" className="form-control" id="password" value={password} placeholder="Enter Password" minLength={8} required onChange={(e) => setPassword(e.target.value)}/>
                    </div>
                    <div className="form-group">
                        <label><b>USERTYPE</b></label>
                        <br />
                        <div className="form-check form-check-inline">
                        <input className="form-check-input" type="radio" name="usertype" value="FARMER" checked={userType === "FARMER"} onChange={(e) => setUserType(e.target.value)}/>
                        <label className="form-check-label">FARMER</label>
                        </div>
                        <div className="form-check form-check-inline">
                        <input className="form-check-input" type="radio" name="usertype" value="CUSTOMER" checked={userType === "CUSTOMER"} onChange={(e) => setUserType(e.target.value)}/>
                        <label className="form-check-label">CUSTOMER</label>
                        </div>
                    </div>
                    <div className="form-group">
                        {/* <label htmlFor="email"><b>EMAIL</b></label> */}
                        <input type="email" className="form-control" id="email" aria-describedby="emailHelp" value={email} placeholder="Enter Email" onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                    <div className="form-group">
                        {/* <label htmlFor="address"><b>ADDRESS</b></label> */}
                        <input type="text" className="form-control" name="address" value={address} placeholder="Enter Address" required onChange={(e) => setAddress(e.target.value)}/>
                    </div>
                    <div className="form-group">
                        {/* <label htmlFor="city"><b>CITY</b></label> */}
                        <input type="text" className="form-control" name="city" value={city} placeholder="Enter City" required onChange={(e) => setCity(e.target.value)}/>
                    </div>
                    <div className="form-group">
                        {/* <label htmlFor="phoneNumber"><b>PHONE NUMBER</b></label> */}
                        <input type="tel" className="form-control" name="phoneNumber" value={phoneNumber} placeholder="Enter Phone Number (eg - 9876543210)" pattern="[0-9]{10}" required onChange={(e) => setPhoneNumber(e.target.value)}/>
                    </div>
                    <br />
                    <button type="submit" className="btn btn-primary">SUBMIT</button>
                </form>
                
            </div>
        </Layout>
    )
}

export default Register;