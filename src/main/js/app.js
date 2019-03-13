'use strict';

// tag::vars[]
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
// end::vars[]
const follow = require('./follow');

var root = '/api';

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			users: [],
			pageSize: 2
		};
	}

	loadFromServer(pageSize) {
    		follow(client, root, [
    			{rel: 'users', params: {size: pageSize}}]
    		).then(employeeCollection => {
    			return client({
    				method: 'GET',
    				path: employeeCollection.entity._links.profile.href,
    				headers: {'Accept': 'application/schema+json'}
    			}).then(schema => {
    				this.schema = schema.entity;
    				return employeeCollection;
    			});
    		}).done(employeeCollection => {
    			this.setState({
    				users: employeeCollection.entity._embedded.users,
    				attributes: Object.keys(this.schema.properties),
    				pageSize: pageSize,
    				links: employeeCollection.entity._links});
    		});
    	}

	componentDidMount() {
//		client({ method: 'GET', path: '/api/users' }).done(response => {
//			this.setState({ users: response.entity._embedded.users });
        this.loadFromServer(this.state.pageSize);
//		});
	}

	render() {
		return (
			<EmployeeList users={this.state.users} />
		)
	}
}
// end::app[]

// tag::employee-list[]
class EmployeeList extends React.Component {
	render() {
		console.log(this.props.users);
		var users = this.props.users.map(user =>
			<Employee key={user._links.self.href} user={user} />
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
class Employee extends React.Component {
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