import React from "react";


function Footer () {
    return <footer><p>
        Copyright © {new Date().getFullYear()}
    </p></footer>
}
Footer.displayName="Footer";
export default Footer