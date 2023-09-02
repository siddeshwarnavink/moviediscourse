@extends('layouts.main')

@section('title') Home @endsection
@section('head')
<style>
    a.movie-link {
        text-decoration:none;
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
    <h2 class="py-3">Top movies</h2>
    <div class="row">
        @foreach ($movies as $item)
            <div class="col-lg-3 col-md-4 col-sm-6">
                <a href="/movie/{{ $item->id }}" class="movie-link">
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
@endsection