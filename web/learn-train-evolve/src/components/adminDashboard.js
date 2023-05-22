import React, {useState} from "react";
import '../styles.css';

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
    };

    const handleAddUser = () => {
        // Perform logic to add the user using the userDetails state
        // Axios client to call the lambda function
        console.log('User details:', userDetails);
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

    return (<div>
        <h1>Administrator Dashboard</h1>
        {!isAddingUser ? (
            <button onClick={() => setIsAddingUser(true)}>Add New Member</button>
        ) : (
            <form>
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
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
                <input
                    type="text"
                    name="membership"
                    placeholder="Membership"
                    value={userDetails.membership}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="rank"
                    placeholder="Rank"
                    value={userDetails.rank}
                    onChange={handleInputChange}
                />
                <button type="button" onClick={handleAddUser}>
                    Add Member
                </button>
            </form>
        )}
    </div>
    );
};

export default AdminDashboard;