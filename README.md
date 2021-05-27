# kysokApp
Kysok Interview test project

The project tests creation of a HTTP service that handles crud operations for configs.
This operations have to satisfy certain requirments



### Enviromet & Setup

 1.Docker
 2.Virtual box
 3.Kubernetes Using (Minikube)
 4.Network gateways (Istio-ingress)
 
 
 ### Endpoints
Following are the endpoints are implemented:

| Name   | Method      | URL
| ---    | ---         | ---
| List   | `GET`       | `/configs`
| Create | `POST`      | `/configs`
| Get    | `GET`       | `/configs/{name}`
| Update | `PUT/PATCH` | `/configs/{name}`
| Delete | `DELETE`    | `/configs/{name}`
| Query  | `GET`       | `/search?metadata.key=value`

### Samples
![alt text](https://github.com/erickogi/kysokApp/blob/master/image1.jpg?raw=true)
![alt text](https://github.com/erickogi/kysokApp/blob/master/image2.jpg?raw=true)
![alt text](https://github.com/erickogi/kysokApp/blob/master/image3.jpg?raw=true)


 
 

