<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:if test="${doesItAlreadyExist == false}">
    <form action="/employees" method="post">
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="employeeName"></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="employeeSurname"></td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td><input type="date" name="employeeDOB"></td>
            </tr>
            <tr>
                <td>Phone number</td>
                <td><input type="text" name="employeePhoneNumber"></td>
            </tr>
            <tr>
                <td>Position</td>
                <td>
                    <select name="employeePosition">
                        <option>Waiter</option>
                        <option>Cook</option>
                        <option>Manager</option>
                        <option>Cleaner</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Salary</td>
                <td><input type="text" name="employeeSalary"></td>
            </tr>

        </table>
        <input style="margin-left: 25px" type="submit" value="Submit" name="submit">
    </form>
    </c:if>

    <c:if test="${doesItAlreadyExist == true}">
    <form action="/updatedEmployeeId=${employee.ID}" method="post">
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="employeeName" value="${employee.firstName}"></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="employeeSurname" value="${employee.lastName}"></td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td><input type="date" name="employeeDOB" value="${employee.dateOfBirth}"></td>
            </tr>
            <tr>
                <td>Phone number</td>
                <td><input type="text" name="employeePhoneNumber" value="${employee.phoneNumber}"></td>
            </tr>
            <tr>
                <td>Position</td>
                <td>
                    <select name="employeePosition">
                        <option>Waiter</option>
                        <option>Cook</option>
                        <option>Manager</option>
                        <option>Cleaner</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Salary</td>
                <td><input type="text" name="employeeSalary" value="${employee.salary}"></td>
            </tr>

        </table>
        <input style="margin-left: 25px" type="submit" value="Submit" name="submit">
    </form>
    </c:if>

</body>
</html>
