import React from "react";
import "./Content.css"


function Content (props) {
    const className = "myClass " + props.className;

    return <div className={className}>
        <h1>{props.heading}</h1>
        <p>{props.content}</p>
    </div>
}

export default Content;