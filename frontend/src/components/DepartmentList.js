import React, { useEffect, useState } from "react";
import axios from "axios";

function DepartmentList() {
    const [deptIds, setDeptIds] = useState([]);       // store dept IDs for listing
    const [departments, setDepartments] = useState([]);

    useEffect(() => {
        fetchAllDepartments();
    }, []);

    const fetchAllDepartments = async () => {
        try {
            const response = await axios.get("/api/departments");
            setDepartments(response.data);
        } catch (err) {
            console.error("Error fetching departments", err);
        }
    };

    const handleDelete = async (id) => {
        if (window.confirm("Delete this department?")) {
            try {
                await axios.delete(`/api/departments/${id}`);
                alert("Deleted department with ID " + id);
                fetchAllDepartments();
            } catch (err) {
                console.error("Error deleting department", err);
            }
        }
    };

    return (
        <div>
            {departments && departments.length > 0 ? (
                departments.map((dept) => (
                    <div key={dept.id} style={{ border: "1px solid #ccc", margin: 8, padding: 8 }}>
                        <p><strong>ID:</strong> {dept.id}</p>
                        <p><strong>Name:</strong> {dept.name}</p>
                        <button onClick={() => handleDelete(dept.id)}>Delete</button>
                    </div>
                ))
            ) : (
                <p>No departments found.</p>
            )}
        </div>
    );
}

export default DepartmentList;
