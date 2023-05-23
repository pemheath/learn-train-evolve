import React, {useState} from "react";
import '../styles.css';
import axios from 'axios';


const AdminDashboard = () => {
    const[isAddingUser, setIsAddingUser] = useState(false);

    const [userDetails, setUserDetails] = useState({
        email: '',
        firstName: '',
        lastName: '',
        membership: '',
        rank: '',
    });

    const handleInputChange = (e) => {
        setUserDetails((prevState) => ({
            ...prevState,
            [e.target.name]: e.target.value,
        }));
        console.log(e.target.name);
        console.log(e.target.value);
    };

    const handleAddUser = async () => {
        //  logic to add the user using the userDetails, calling the /users POST endpoint
        const userData = {
            email: userDetails.email,
            firstName: userDetails.firstName,
            lastName: userDetails.lastName,
            membership: userDetails.membership,
            rank: userDetails.rank,
        };
        console.log('User data:', userData);
        console.log('User data JSON:', JSON.stringify(userData));

        try{

            const response = await axios.post(`${process.env.REACT_APP_API_BASE_URL}/users`, userData, {
            });
            if (response.status==200) {
                console.log('Success');
                const data = await response.data;
                console.log('Data:', data);
                return data;
            } else {
                console.log('Error');
                return response;

            }

        } catch (error) {
            handleError(error);
        }

        // Reset the form
        setUserDetails({
            email: '',
            firstName: '',
            lastName: '',
            membership: '',
            rank: '',
        });
        setIsAddingUser(false);
    };

    const handleError = (error) => {
        if (error.response) {
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
        } else if (error.request) {
            // The request was made but no response was received
            // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
            // http.ClientRequest in node.js
            console.log(error.request);
        } else {
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message);
        }
    }

    return (<div>
        <h1>Administrator Dashboard</h1>
        {!isAddingUser ? (
            <button onClick={() => setIsAddingUser(true)}>Add New Member</button>
        ) : (
            <form>
                <input
                    type="email"
                    name="email"
                    placeholder="member email"
                    value={userDetails.email}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                    value={userDetails.firstName}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="lastName"
                    placeholder="Last Name"
                    value={userDetails.lastName}
                    onChange={handleInputChange}
                />
                <select
                    id="membership"
                    name="membership"
                    value={userDetails.membership}
                    onChange={handleInputChange}
                >
                    <option value="">Select Membership Option</option>
                    <option value="Unlimited">Unlimited Monthly</option>
                    <option value="UnlimitedPlus">Unlimited Monthly Plus</option>
                    <option value="Limited12">Limited Twelve: Twelve Classes Monthly</option>
                    <option value="Limited6">Limited Six: Six Classes Monthly</option>
                    <option value="PayPerClass">Drop-In</option>

                </select>
                <select
                    id="rank"
                    name="rank"
                    value={userDetails.rank}
                    onChange={handleInputChange}
                >
                    <option value="">Select Current Rank</option>
                    <option value="White1">White 1 Stripe</option>
                    <option value="White2">White 2 Stripes</option>
                    <option value="White3">White 3 Stripes</option>
                    <option value="White4">White 4 Stripes</option>
                    <option value="Blue">Blue</option>
                    <option value="Purple">Purple</option>
                    <option value="Brown">Brown</option>
                    <option value="Black">Black</option>
                </select>
                <button type="button" onClick={handleAddUser}>
                    Add Member
                </button>
            </form>
        )}
    </div>
    );
};

export default AdminDashboard;