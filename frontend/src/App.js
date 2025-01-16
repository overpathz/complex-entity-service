import React from "react";
import EmployeeForm from "./components/EmployeeForm";
import EmployeeList from "./components/EmployeeList";
import DepartmentForm from "./components/DepartmentForm";
import DepartmentList from "./components/DepartmentList";

function App() {
    return (
        <div style={{ margin: "20px" }}>
            <h1>Admin Panel</h1>
            <section style={{ display: "flex", gap: "20px", marginBottom: "40px" }}>
                <div>
                    <h2>Create Employee</h2>
                    <EmployeeForm />
                    <h2>Employees</h2>
                    <EmployeeList />
                </div>
                <div>
                    <h2>Create Department</h2>
                    <DepartmentForm />
                    <h2>Departments</h2>
                    <DepartmentList />
                </div>
            </section>
        </div>
    );
}

export default App;
