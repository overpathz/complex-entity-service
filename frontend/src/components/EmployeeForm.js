import React, { useState } from "react";
import axios from "axios";

function EmployeeForm() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [departmentId, setDepartmentId] = useState("");
    const [projectIds, setProjectIds] = useState("");
    const [address, setAddress] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [emergencyContact, setEmergencyContact] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        const projectIdList = projectIds
            .split(",")
            .map(str => str.trim())
            .filter(str => str.length > 0)
            .map(str => parseInt(str, 10));

        const payload = {
            name,
            email,
            departmentId: departmentId ? parseInt(departmentId, 10) : null,
            projectIds: projectIdList,
            userProfile: {
                address,
                phoneNumber,
                emergencyContact
            }
        };

        try {
            await axios.post("/api/employees", payload);
            alert("Employee created!");
            setName("");
            setEmail("");
            setDepartmentId("");
            setProjectIds("");
            setAddress("");
            setPhoneNumber("");
            setEmergencyContact("");
        } catch (err) {
            console.error("Error creating employee:", err);
        }
    };

    return (
        <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", maxWidth: 300 }}>
            <label>Name</label>
            <input value={name} onChange={(e) => setName(e.target.value)} />

            <label>Email</label>
            <input value={email} onChange={(e) => setEmail(e.target.value)} />

            <label>Department ID (optional)</label>
            <input value={departmentId} onChange={(e) => setDepartmentId(e.target.value)} />

            <label>Project IDs (comma separated)</label>
            <input value={projectIds} onChange={(e) => setProjectIds(e.target.value)} />

            <label>Address</label>
            <input value={address} onChange={(e) => setAddress(e.target.value)} />

            <label>Phone Number</label>
            <input value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />

            <label>Emergency Contact</label>
            <input value={emergencyContact} onChange={(e) => setEmergencyContact(e.target.value)} />

            <button type="submit" style={{ marginTop: 10 }}>Submit</button>
        </form>
    );
}

export default EmployeeForm;
