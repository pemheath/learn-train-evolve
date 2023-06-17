import React from "react";
import App from "../App";

function Footer () {
    return <footer><p>
        Copyright © {new Date().getFullYear()}
    </p></footer>
}
Footer.displayName="Footer";
export default Footer