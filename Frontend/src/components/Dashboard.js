// import React from 'react';
// import Container from 'react-bootstrap/Container';
// import Nav from 'react-bootstrap/Nav';
// import Navbar from 'react-bootstrap/Navbar';
// import NavDropdown from 'react-bootstrap/NavDropdown';
// // import { Container, Nav, Navbar, NavDropdown } from 'react-bootstrap';
// // import { Container } from 'react-bootstrap';
// // import { Nav } from 'react-bootstrap';
// // import { Navbar } from 'react-bootstrap';
// // import { NavDropdown } from 'react-bootstrap';

// function Dashboard() {
//   return (
//       <Navbar expand="lg" className="bg-body-secondary">
//       <Container>
//         <Navbar.Brand href="#home" className="pe-5 ms-0">Customer Service Portal</Navbar.Brand>
//         <Navbar.Toggle aria-controls="basic-navbar-nav" />
//         <Navbar.Collapse id="basic-navbar-nav">
//           <Nav className="me-auto">
//             <Nav.Link href="#home" className="px-5">Home</Nav.Link>
//             <Nav.Link href="#link" className="px-5">About</Nav.Link>
//             <Nav.Link href="#link" className="px-5">Contact</Nav.Link>
//             <Nav.Link href="#link" className="px-5">Link</Nav.Link>
//             <Nav.Link href="#link" className="mx-5 px-3 bg-danger text-light rounded align-right">Logout</Nav.Link>
//             {/* <NavDropdown title="Dropdown" id="basic-nav-dropdown">
//               <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
//               <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
//               <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
//               <NavDropdown.Divider />
//               <NavDropdown.Item href="#action/3.4">
//                 Separated link
//               </NavDropdown.Item>
//             </NavDropdown> */}
//           </Nav>
//         </Navbar.Collapse>
//       </Container>
//     </Navbar>
//   );
// }

// export default Dashboard



import React from "react";
import { Navbar, Nav, NavItem, Card, Col, Row, Container } from "react-bootstrap";
// import "./Dashboard.css";

const Dashboard = () => {
  return (
    <div className="Dashboard"> 
      <Navbar expand="lg" bg="dark" variant="dark">
        <Container>
          <Navbar.Brand href="/Dashboard">Customer Service Portal</Navbar.Brand>
          <Nav>
            <NavItem>
              <Nav.Link href="#">Home</Nav.Link>
            </NavItem>
            <NavItem>
              <Nav.Link href="#">About</Nav.Link>
            </NavItem>
            <NavItem>
              <Nav.Link href="#">Contact</Nav.Link>
            </NavItem>
          </Nav>
        </Container>
      </Navbar>
      <Row>
        <Col>
          <Card>
            <Card.Body>
              <Card.Title className="card-title">Card title 1</Card.Title>
              <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </Card.Body>
          </Card>
        </Col>
        <Col>
          <Card>
            <Card.Body>
              <Card.Title className="card-title">Card title 2</Card.Title>
              <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </Card.Body>
          </Card>
        </Col>
        <Col>
          <Card>
            <Card.Body>
              <Card.Title className="card-title">Card title 3</Card.Title>
              <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </div>
  );
};

export default Dashboard;