@extends('layouts.main')

@section('title') Movie detail @endsection
@section('head')
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<style>
    .movie-title {
        font-size: 1.7em;
        padding: 0;
        margin: 0;
    }

    .star-rating {
        color: #FFC107;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    
    .release-rating {
        font-size: 14px;
        color: #888;
    }

    .moviedetails {
        background-color: #f7f7f7;
        padding: 20px;
    }

    .moviedetails .card-title {
        font-size: 1em;
    }

    .list-group-item {
        background-color: transparent;
        border-left: none;
        border-right: none;
        border-top: none;
        padding-left: 0;
    }

    .icon-row {
        display: flex;
        margin-right: 12px;
    }

    .icon-row .fa {
        margin-right: 5px;
    }
    .movie-tags {
        display: flex;
        flex-wrap: wrap;
    }
    .badge {
        margin-right: 5px;
        padding: 6px 10px;
        font-size: .8em;
        margin-bottom: 5px;
    }

    .description {
        padding-right: 18px;
    }
    .comment-list {
        display: block;
        list-style-type: none;
        padding-left: 0;
    }

    .comment {
        margin-bottom: 20px;
    }

    .comment .comment-list {
        margin-top: 15px;
        margin-left: 50px;
    }

    .comment-user {
        display: flex;
        align-items: center;
    }

    .comment-avatar {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-right: 10px;
        background-color: #ccc;
    }

    .comment-content {
        background-color: #f5f5f5;
        padding: 10px;
        border-radius: 5px;
        margin-left: 50px;
    }

    .comment-actions {
        margin-top: 10px;
    }

    .reply-button {
        color: #007bff;
        cursor: pointer;
    }
</style>
@endsection
@section('content')
<div class="row mt-3">
    <!-- Title -->
    <div class="col-md-8">
        <h1 class="movie-title">Movie Name</h1>
        <p class="release-rating">
            <span>Release Date: January 1, 2023</span>
            <span class="ml-3">Age Rating: PG-13</span>
        </p>
    </div>
    <div class="col-md-4 text-center star-rating">
        <i class="fa fa-star"></i>
        <p class="mb-0 ml-2">4.5</p>
    </div>
    <!-- Thumbnail/Trailer -->
    <div class="row">
        <div class="col-3 thumbnail">
            <img src="https://picfiles.alphacoders.com/101/101684.jpg" alt="Movie Thumbnail" class="img-fluid">
        </div>
        <div class="col-5 trailer">
            <iframe width="100%" height="100%" src="https://www.youtube.com/embed/zSWdZVtXT7E?autoplay=1" frameborder="0" allowfullscreen></iframe>
        </div>
        <div class="col-3 card moviedetails">
            <h5 class="card-title">Movie Details</h5>
            <div class="row">
                <div class="col-2 icon-row">
                    <i class="fa fa-heart"></i>
                    <span>4.5</span>
                </div>
                <div class="col-2 icon-row">
                    <i class="fa fa-comment"></i>
                    <span>123</span>
                </div>
            </div>
            <ul class="list-group mt-3">
                <li class="list-group-item"><b>Director:</b> John Doe</li>
                <li class="list-group-item"><b>Writer:</b> Jane Smith</li>
            </ul>
        </div>
    </div>
    <!-- Description -->
    <div class="movie-tags mt-3 mb-1">
        <span class="badge badge-secondary">Action</span>
        <span class="badge badge-secondary">Adventure</span>
        <span class="badge badge-secondary">Sci-Fi</span>
    </div>
    <p class="description">When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.</p>

</div>

    <!-- Comments -->
    <h5 class="my-2">Reviews</h5>
    <ul class="comment-list">
            <!-- Root comments -->
            <li class="comment">
                <div class="comment-user">
                    <img src="/assets/profile-picture.png" alt="User Avatar" class="comment-avatar">
                    <span class="comment-username">User1</span>
                </div>
                <div class="comment-content">
                    <p>This is a top-level comment.</p>
                    <div class="comment-actions">
                        <span class="reply-button">Reply</span>
                    </div>
                </div>

                <!-- Nested comments -->
                <ul class="comment-list">
                    <li class="comment">
                        <div class="comment-user">
                            <img src="/assets/profile-picture.png" alt="User Avatar" class="comment-avatar">
                            <span class="comment-username">User2</span>
                        </div>
                        <div class="comment-content">
                            <p>This is a nested comment.</p>
                            <div class="comment-actions">
                                <span class="reply-button">Reply</span>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>

            <!-- Add more comments here as needed -->
        </ul>
@endsection