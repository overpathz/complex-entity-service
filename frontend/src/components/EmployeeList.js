import React, { useEffect, useState } from "react";
import axios from "axios";

function EmployeeList() {
    const [employees, setEmployees] = useState([]);

    const fetchEmployees = async () => {
        try {
            const response = await axios.get("/api/employees");
            setEmployees(response.data);
        } catch (err) {
            console.error("Error loading employees:", err);
        }
    };

    useEffect(() => {
        fetchEmployees();
    }, []);

    const handleDelete = async (id) => {
        if (window.confirm("Delete this employee?")) {
            try {
                await axios.delete(`/api/employees/${id}`);
                fetchEmployees();
            } catch (err) {
                console.error("Error deleting employee:", err);
            }
        }
    };

    return (
        <div>
            {employees.map((emp) => (
                <div
                    key={emp.id}
                    style={{ border: "1px solid #ccc", margin: "8px", padding: "8px" }}
                >
                    <p><strong>ID:</strong> {emp.id}</p>
                    <p><strong>Name:</strong> {emp.name}</p>
                    <p><strong>Email:</strong> {emp.email}</p>
                    <p><strong>Department ID:</strong> {emp.departmentId || "N/A"}</p>
                    <p><strong>Projects:</strong> {emp.projectIds ? emp.projectIds.join(", ") : "N/A"}</p>
                    {emp.userProfile && (
                        <p>
                            <strong>Address:</strong> {emp.userProfile.address} <br />
                            <strong>Phone:</strong> {emp.userProfile.phoneNumber} <br />
                            <strong>Emergency Contact:</strong> {emp.userProfile.emergencyContact}
                        </p>
                    )}
                    <button onClick={() => handleDelete(emp.id)}>Delete</button>
                </div>
            ))}
        </div>
    );
}

export default EmployeeList;
