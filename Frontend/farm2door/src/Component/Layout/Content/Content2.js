import React from "react";
import "./Content.css"


function Content2 (props) {
    const className = "listing " + props.className;

    return <div className={className}>
        <h1>{props.heading}</h1>
        <ul>
            {props.list.map((l) =>
                <li><b>{l.title}:</b> {l.content}</li>
            )}
        </ul>
    </div>
}

export default Content2;