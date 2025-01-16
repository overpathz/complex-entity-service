import React, { useState } from "react";
import axios from "axios";

function DepartmentForm() {
    const [name, setName] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        const payload = { name };
        try {
            await axios.post("/api/departments", payload);
            alert("Department created!");
            setName("");
        } catch (err) {
            console.error("Error creating department:", err);
        }
    };

    return (
        <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", maxWidth: 300 }}>
            <label>Department Name</label>
            <input value={name} onChange={(e) => setName(e.target.value)} />
            <button type="submit" style={{ marginTop: 10 }}>Create Department</button>
        </form>
    );
}

export default DepartmentForm;
