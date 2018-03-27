'use strict';

// tag::vars[]
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
// end::vars[]

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {users: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/users'}).done(response => {
			this.setState({users: response.entity._embedded.users});
		});
	}

	render() {
		return (
			<EmployeeList users={this.state.users}/>
		)
	}
}
// end::app[]

// tag::employee-list[]
class EmployeeList extends React.Component{
	render() {
	    console.log(this.props.users);
		var users = this.props.users.map(user =>
			<Employee key={user._links.self.href} user={user}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>E-mail</th>
						<th>Pwd xDD</th>
					</tr>
					{users}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.user.name}</td>
				<td>{this.props.user.email}</td>
				<td>{this.props.user.password}</td>
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]