# kysokApp
Kysok Interview test project

The project tests creation of a HTTP service that handles crud operations for configs.
This operations have to satisfy certain requirments


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
![alt text](https://github.com/erickogi/kysokApp/blob/master/image1.png?raw=true)
![alt text](https://github.com/erickogi/kysokApp/blob/master/image2.png?raw=true)
![alt text](https://github.com/erickogi/kysokApp/blob/master/image3.png?raw=true)




### Enviromet & Setup

1. Docker 
 
2. Virtual box - To easily run Minikube with docker images in MacOS 

3. Kubernetes Using (Minikube) -  
>brew install minikube
>minikube start — vm-driver=virtualbox

4. Network gateways (Istio-ingress) - To expose our service
 Install & Setup -> https://istio.io/latest/docs/setup/getting-started/
 Execute istio.yml  Location -> ( kysokApp -> istio.yml)
 
 >kubectl apply -f istio.yml  
 
 
5. Create namespace for kyosk app

>kubectl create namespace kyoskapp

6. Create & injectistio gateway
Excute kyoskapp-gateway.yml

>kubectl apply -f myapp-gateway.yml
>kubectl label namespace kyoskapp istio-injection=enabled

7. Create docker image

>docker build -t kysokapp .

8. Deploy Location -> (kysokApp -> kysokapp.yml)

>kubectl apply -f kysokapp.yml

9. Forward port 

>kubectl port-forward services/kysokapp 8080:8080 -n kysokapp




 
 
 
 
 



 
 

