import {React, useState} from "react";
import Layout from "../../../Component/Layout/Layout";
import "../Auth.css"
import axios from "axios";
import toast from 'react-hot-toast';
import { useNavigate } from "react-router-dom";

function ImageUpload() {
    const [file, setFile] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        const renamedFile = new File([file], 'newFileName.jpg', { type: file.type });
        const formData = new FormData();
        formData.append('file', renamedFile);
        axios.post('http://localhost:8080/api/files/upload', formData)
        .then(response => {
            console.log('File uploaded successfully:', response.data);
        })
        .catch(error => {
            console.error('Error uploading file:', error);
        });
        // try{
        //     const res = await axios.post(
        //         "http://localhost:8080/auth/login",
        //         data,
        //         {
        //             validateStatus: function (status) {
        //                 return status >= 200 && status < 500;
        //             },
        //         }
        //     );

        //     if(res.status == 200){
        //         const data = {...res.data, raw_password: password};
        //         // login(data.userId, data.role);
        //         toast.success("Welcome back " + data.firstname + " !");
        //         localStorage.setItem('auth', JSON.stringify(res.data));
        //         setTimeout(() => {
        //             navigate("/dashboard");
        //         }, 10);
        //     }
        //     else if (res.status <= 450) {
        //         toast.error(res.data);
        //     }
        // } catch(error) {
        //     toast.error("Something went wrong !");
        // }

        
    };


    return (
        <Layout>
            <div className="authForm loginForm">
                <h2>UPLOAD IMAGE</h2>
                <form onSubmit={handleSubmit} method="POST" enctype="multipart/form-data">
                    <div className="form-group">
                        <label htmlFor="password"><b>IMAGE</b></label>
                        <input type="file" className="form-control" id="password" accept=".jpeg, .jpg, .png" required onChange={(e) => setFile(e.target.files[0])}/>
                    </div>
                    <br />
                    <button type="submit" className="btn btn-primary">SUBMIT</button>
                </form>
            </div>
        </Layout>
    )
}

export default ImageUpload;