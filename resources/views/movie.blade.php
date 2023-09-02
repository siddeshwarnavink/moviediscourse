@extends('layouts.main')

@section('title') {{ $movie->name }} @endsection
@section('head')
<meta name="csrf-token" content="{{ csrf_token() }}">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<script> 
    window.MOVIE={id:"{{ $movie->id }}", name:"{{ $movie->name }}"};
    @guest
    window.USER=null;
    @endguest
    @auth
    window.USER={id:{{ Auth::user()->id }}, name:"{{ Auth::user()->name }}"};
    @endauth
</script>
@viteReactRefresh
@vite('resources/js/app.js')
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
        <h1 class="movie-title">{{ $movie->name }}</h1>
        <p class="release-rating">
            <span>Release: {{ $movie->release_date }}</span>
            <span class="ml-3">Age Rating: {{ $movie->age_rating }}</span>
        </p>
    </div>
    <div class="col-md-4 text-center star-rating">
        <i class="fa fa-star"></i>
        <p class="mb-0 ml-2">{{ $movie->rating }}</p>
    </div>
    <!-- Thumbnail/Trailer -->
    <div class="row">
        <div class="col-3 thumbnail">
            <img src="{{ $movie->thumbnail }}" alt="Movie Thumbnail" class="img-fluid">
        </div>
        <div class="col-5 trailer">
            <iframe width="100%" height="100%" src="https://www.youtube.com/embed/{{ $movie->youtube_trailer }}?autoplay=1" frameborder="0" allowfullscreen></iframe>
        </div>
        <div class="col-3 card moviedetails">
            <h5 class="card-title">Movie Details</h5>
            <div class="row">
                <div class="col-2 icon-row">
                    <i class="fa fa-star"></i>
                    <span>{{ $movie->rating }}</span>
                </div>
                <div class="col-2 icon-row">
                    <i class="fa fa-comment"></i>
                    <span>{{ $movie->comments_count }}</span>
                </div>
            </div>
            <ul class="list-group mt-3">
                <li class="list-group-item"><b>Director:</b> {{ $movie->director }}</li>
                <li class="list-group-item"><b>Writer:</b>  {{ $movie->writer }}</li>
            </ul>
        </div>
    </div>
</div>
<!-- Description -->
<div class="movie-tags mt-3 mb-1">
    @php
        $tags = explode(';', $movie->tags);
    @endphp
    @foreach ($tags as $tag)
        <span class="badge badge-secondary">{{ $tag }}</span>
    @endforeach
</div>
<p class="description">{{ $movie->short_description }}</p>
<!-- Comments -->
<div id="react-comments"></div>
@endsection