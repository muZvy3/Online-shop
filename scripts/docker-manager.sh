#!/bin/bash

# Online Shop Docker Manager
set -e

echo "=== Online Book Shop Docker Manager ==="

GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m'

print_info() { echo -e "${GREEN}[INFO]${NC} $1"; }
print_docker() { echo -e "${BLUE}[DOCKER]${NC} $1"; }
print_error() { echo -e "${RED}[ERROR]${NC} $1"; }

build_app() {
    print_info "Building Spring Boot application..."
    chmod +x mvnw
    ./mvnw clean package -DskipTests
    print_info "Application built successfully"
}

case "$1" in
    "up")
        print_docker "Starting Online Book Shop with Docker Compose..."
        build_app
        docker compose up -d
        print_info "Application is starting..."
        print_info "Frontend: http://localhost:8080"
        print_info "Database: localhost:5432"
        ;;
        
    "down")
        print_docker "Stopping services..."
        docker compose down
        ;;
        
    "restart")
        print_docker "Restarting services..."
        docker compose restart
        ;;
        
    "logs")
        print_docker "Showing application logs..."
        docker compose logs -f app
        ;;
        
    "db-logs")
        print_docker "Showing database logs..."
        docker compose logs -f postgres
        ;;
        
    "build")
        print_docker "Building images..."
        build_app
        docker compose build
        ;;
        
    "clean")
        print_docker "Cleaning up Docker resources..."
        docker compose down -v
        docker system prune -f
        ;;
        
    "status")
        print_docker "Container status:"
        docker compose ps
        ;;
        
    "db-connect")
        print_docker "Connecting to database..."
        docker compose exec postgres psql -U user_book -d online_shop
        ;;
        
    "backup")
        print_docker "Creating database backup..."
        docker compose exec postgres pg_dump -U user_book online_shop > backup_$(date +%Y%m%d_%H%M%S).sql
        print_info "Backup created: backup_$(date +%Y%m%d_%H%M%S).sql"
        ;;
        
    *)
        echo "Usage: $0 {up|down|restart|logs|db-logs|build|clean|status|db-connect|backup}"
        echo "  up         - Build and start services"
        echo "  down       - Stop services"
        echo "  restart    - Restart services"
        echo "  logs       - Show application logs"
        echo "  db-logs    - Show database logs"
        echo "  build      - Build images"
        echo "  clean      - Stop and remove all resources"
        echo "  status     - Show container status"
        echo "  db-connect - Connect to database"
        echo "  backup     - Create database backup"
        exit 1
        ;;
esac