@extends('layouts.main')

@section('title') Home @endsection
@section('head')
<style>
    .title {
        font-size: 1.7em;
    }

    a.movie-link {
        text-decoration: none;
        color: black;
    }

    .movie-card {
        border: 1px solid #ddd;
        margin-bottom: 20px;
        padding: 10px;
    }

    .movie-card img {
        max-width: 100%;
        height: auto;
    }

    .movie-title {
        font-weight: bold;
        font-size: 1.2rem;
        margin-top: 10px;
    }

    .star-rating {
        color: #FFC107;
        font-size: 1.2rem;
        margin-top: 5px;
    }
</style>
@endsection

@section('content')

@if (sizeof($recommended_movies) > 0)
<div>
    <h2 class="title pt-3 pb-1">Recommended Movies</h2>
    <div class="row">
        @foreach ($recommended_movies as $item)
        <div class="col-lg-3 col-md-4 col-sm-6">
            <a href="{{ route('movie',$item->id)}}" class="movie-link">
                <div class="movie-card">
                    <img src="{{ $item->thumbnail }}" alt="Movie Poster">
                    <div class="movie-title">{{ $item->name }}</div>
                    <div class="star-rating">
                        ★ {{ $item->rating }}
                    </div>
                </div>
            </a>
        </div>
        @endforeach
    </div>
</div>
@endif


@if (sizeof($top_movies) > 0)
<div>
    <h2 class="title pt-3 pb-1">Top movies</h2>
    <div class="row">
        @foreach ($top_movies as $item)
        <div class="col-lg-3 col-md-4 col-sm-6">
            <a href="{{ route('movie',$item->id)}}" class="movie-link">
                <div class="movie-card">
                    <img src="{{ $item->thumbnail }}" alt="Movie Poster">
                    <div class="movie-title">{{ $item->name }}</div>
                    <div class="star-rating">
                        ★ {{ $item->rating }}
                    </div>
                </div>
            </a>
        </div>
        @endforeach
    </div>
</div>
@endif

@endsection